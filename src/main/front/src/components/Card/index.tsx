import React from "react";
import {CardViewModel} from "../../models/CardViewModel";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardHeader from '@material-ui/core/CardHeader';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/DeleteOutline';
import EditIcon from '@material-ui/icons/Edit';
import LocationIcon from '@material-ui/icons/LocationOnOutlined';
import "./Card.less"

interface Props extends CardViewModel {
    onDelete: (cardId: number) => void
    onEdit: (card: CardViewModel) => void
}

const MyCard: React.FC<Props> = ({onDelete, onEdit, ...card}) => (
    <Card>
        <CardHeader
            action={
                <>
                    <IconButton aria-label="settings" onClick={() => onEdit(card)}>
                        <EditIcon fontSize="small"/>
                    </IconButton>
                    <IconButton aria-label="settings" onClick={() => onDelete(card.cardId)}>
                        <DeleteIcon fontSize="small"/>
                    </IconButton>
                </>
            }
            title={card.title}
            subheader={card.username}
        />
        <CardContent>
            {card.locationString ? (
                <div className="Card-field">
                    <LocationIcon fontSize="small"/>
                    <span>{card.locationString}</span>
                </div>
             ) : null}
        </CardContent>
    </Card>
);

export default MyCard;